package com.store.product;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * A very simple model of a product
 * @author Amandeep Saini
 *
 */
public class Product implements Serializable{

    private static final long serialVersionUID = -1037817988718486284L;
    private String imageUrl;
    private String title;
    private String category;
    private boolean isActive;
    private long itemId;
    private String parentCategory;
    private String department;
    private String upc;
    private String brand;
    private Date createdDate;
    private Date modifiedDate;
    private long itemHashint64;
    private Collection<String> keywords;


	public Product(Date createdDate, String imageUrl, String title,
			String category, boolean isActive, long itemId,
			String parentCategory, String department,String upc,
			String brand, Date modifiedDate, long itemHashint64,
			Collection<String> keywords) {

		this.createdDate = createdDate;
		this.imageUrl = imageUrl;
		this.title = title;
		this.category = category;
		this.isActive = isActive;
		this.itemId = itemId;
		this.parentCategory = parentCategory;
		this.department = department;
		this.upc = upc;
		this.brand = brand;
		this.modifiedDate = modifiedDate;
		this.itemHashint64 = itemHashint64;
		this.keywords = keywords;
	}

	public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public long getItemId() {
        return itemId;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public String getDepartment() {
        return department;
    }

    public String getUpc() {
        return upc;
    }

    public String getBrand() {
        return brand;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public long getItemHashint64() {
        return itemHashint64;
    }

    public Collection<String> getKeywords() {
        return keywords;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((brand == null) ? 0 : brand.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = prime * result + (isActive ? 1231 : 1237);
        result = prime * result + (int) (itemHashint64 ^ (itemHashint64 >>> 32));
        result = prime * result + (int) (itemId ^ (itemId >>> 32));
        result = prime * result + ((keywords == null) ? 0 : keywords.hashCode());
        result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
        result = prime * result + ((parentCategory == null) ? 0 : parentCategory.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((upc == null) ? 0 : upc.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        if (brand == null) {
            if (other.brand != null) {
                return false;
            }
        } else if (!brand.equals(other.brand)) {
            return false;
        }
        if (category == null) {
            if (other.category != null) {
                return false;
            }
        } else if (!category.equals(other.category)) {
            return false;
        }
        if (createdDate == null) {
            if (other.createdDate != null) {
                return false;
            }
        } else if (!createdDate.equals(other.createdDate)) {
            return false;
        }
        if (department == null) {
            if (other.department != null) {
                return false;
            }
        } else if (!department.equals(other.department)) {
            return false;
        }
        if (imageUrl == null) {
            if (other.imageUrl != null) {
                return false;
            }
        } else if (!imageUrl.equals(other.imageUrl)) {
            return false;
        }
        if (isActive != other.isActive) {
            return false;
        }
        if (itemHashint64 != other.itemHashint64) {
            return false;
        }
        if (itemId != other.itemId) {
            return false;
        }
        if (keywords == null) {
            if (other.keywords != null) {
                return false;
            }
        } else if (!keywords.equals(other.keywords)) {
            return false;
        }
        if (modifiedDate == null) {
            if (other.modifiedDate != null) {
                return false;
            }
        } else if (!modifiedDate.equals(other.modifiedDate)) {
            return false;
        }
        if (parentCategory == null) {
            if (other.parentCategory != null) {
                return false;
            }
        } else if (!parentCategory.equals(other.parentCategory)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        if (upc == null) {
            if (other.upc != null) {
                return false;
            }
        } else if (!upc.equals(other.upc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product [imageUrl=" + imageUrl + ", title=" + title + ", category=" + category +
            ", isActive=" + isActive + ", itemId=" + itemId + ", parentCategory=" + parentCategory +
            ", department=" + department + ", upc=" + upc + ", brand=" + brand + ", createdDate=" +
            createdDate + ", modifiedDate=" + modifiedDate + ", itemHashint64=" + itemHashint64 + "]";
    }
}
