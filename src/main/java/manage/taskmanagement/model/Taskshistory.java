package manage.taskmanagement.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nabil
 */
@Entity
@Table(name = "taskshistory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taskshistory.findAll", query = "SELECT t FROM Taskshistory t"),
    @NamedQuery(name = "Taskshistory.findByTaskHistoryId", query = "SELECT t FROM Taskshistory t WHERE t.taskHistoryId = :taskHistoryId"),
    @NamedQuery(name = "Taskshistory.findByAssignmentDate", query = "SELECT t FROM Taskshistory t WHERE t.assignmentDate = :assignmentDate")})
public class Taskshistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "task_history_id")
    private Integer taskHistoryId;
    @Basic(optional = false)
    @Column(name = "assignment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignmentDate;
//    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
//    @ManyToOne(optional = false)
//    private Tasks taskId;  
    @Basic(optional = false)
    @Column(name = "task_id")
    private Integer taskId;

//    @JoinColumn(name = "assigned_to_user_id", referencedColumnName = "user_id")
//    @ManyToOne(optional = false)
//    private Integer assignedToUserId;
    @Basic(optional = false)
    @Column(name = "assigned_to_user_id")
    private Integer assignedToUserId;

//    @JoinColumn(name = "assigned_by_user_id", referencedColumnName = "user_id")
//    @ManyToOne(optional = false)
//    private Integer assignedByUserId;
    @Basic(optional = false)
    @Column(name = "assigned_by_user_id")
    private Integer assignedByUserId;

    public Taskshistory() {
    }

    public Taskshistory(Integer taskHistoryId) {
        this.taskHistoryId = taskHistoryId;
    }

    public Taskshistory(Integer taskHistoryId, Date assignmentDate) {
        this.taskHistoryId = taskHistoryId;
        this.assignmentDate = assignmentDate;
    }

    public Integer getTaskHistoryId() {
        return taskHistoryId;
    }

    public void setTaskHistoryId(Integer taskHistoryId) {
        this.taskHistoryId = taskHistoryId;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getAssignedToUserId() {
        return assignedToUserId;
    }

    public void setAssignedToUserId(Integer assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    public Integer getAssignedByUserId() {
        return assignedByUserId;
    }

    public void setAssignedByUserId(Integer assignedByUserId) {
        this.assignedByUserId = assignedByUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskHistoryId != null ? taskHistoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taskshistory)) {
            return false;
        }
        Taskshistory other = (Taskshistory) object;
        if ((this.taskHistoryId == null && other.taskHistoryId != null) || (this.taskHistoryId != null && !this.taskHistoryId.equals(other.taskHistoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manage.taskmanagement.model.Taskshistory[ taskHistoryId=" + taskHistoryId + " ]";
    }

}
