package cz.ibisoft.ibis.api.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
    private String id = UUID.randomUUID().toString();

    public Long getVersion() {
        return version;
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

    protected void setId(String id) {
        this.id = id;
    }
}
