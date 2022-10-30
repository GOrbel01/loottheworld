package org.fsq.security.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.fsq.security.AppProperties;
import org.fsq.security.user.UserPrinciple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Date;

@Service
public class TokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
        Key result = getKey();
        if (result == null) {
            throw new IllegalArgumentException("Invalid Key found");
        }
        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(result)
                .compact();
    }

    private Key getKey() {
        Key result = null;
        try {
            KeyPair pair = loadKeyStore(new File( "./src/main/resources/keystore/ltw-api_xyz.p12"), "Karinyuri366.", "PKCS12");
            result = pair.getPrivate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public static KeyPair loadKeyStore(final File keystoreFile,
                                       final String password, final String keyStoreType)
            throws KeyStoreException, IOException, NoSuchAlgorithmException,
            CertificateException, UnrecoverableKeyException {
        if (null == keystoreFile) {
            throw new IllegalArgumentException("Keystore url may not be null");
        }
//        LOG.info("Initializing key store: {}", keystoreFile.getAbsolutePath());
        final URI keystoreUri = keystoreFile.toURI();
        final URL keystoreUrl = keystoreUri.toURL();
        final KeyStore keystore = KeyStore.getInstance(keyStoreType);
        InputStream is = null;
        try {
            is = keystoreUrl.openStream();
            keystore.load(is, null == password ? null : password.toCharArray());
//            LOG.info("Loaded key store");
        } finally {
            if (null != is) {
                is.close();
            }
        }
        return getKeyPair(keystore, "server", password);
    }

    public static KeyPair getKeyPair(final KeyStore keystore,
                                     final String alias, final String password) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        final Key key = keystore.getKey(alias, password.toCharArray());

        final Certificate cert = keystore.getCertificate(alias);
        final PublicKey publicKey = cert.getPublicKey();
        return new KeyPair(publicKey, (PrivateKey) key);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
