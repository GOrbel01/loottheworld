package fsq.core.entity.item.type;

import javax.persistence.*;

@Entity
@Table(name = "ltw_item_subtype")
public class ItemSubType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemsubtypeid")
    private Integer subTypeId;

    @Column(name = "itemtype")
    private String itemtype;

    @Column(name = "itemsubtypename")
    private String subTypeName;

    public Integer getSubTypeId() {
        return subTypeId;
    }

    public void setSubTypeId(Integer subTypeId) {
        this.subTypeId = subTypeId;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }
}
