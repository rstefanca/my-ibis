package cz.ibisoft.ibis.api.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;
import java.util.UUID;

/**
 * @author Richard Stefanca
 */

@MappedSuperclass
public class BaseEntity {

    @Version
    @Column(nullable = false)
    private Long version = 0L;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModified;

    @Id
    protected String id = UUID.randomUUID().toString();

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
