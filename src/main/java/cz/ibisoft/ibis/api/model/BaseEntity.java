package cz.ibisoft.ibis.api.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

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

    public Long getVersion() {
        return version;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }
}
