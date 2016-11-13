package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.soap.SAAJResult;

/**
 * Created by alex on 12.11.16.
 */

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "group_name")
    private String groupName;

    public Group() {
    }

    public Group(String login, String groupName) {
        this.login = login;
        this.groupName = groupName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (getLogin() != null ? !getLogin().equals(group.getLogin()) : group.getLogin() != null) return false;
        return getGroupName() != null ? getGroupName().equals(group.getGroupName()) : group.getGroupName() == null;

    }

    @Override
    public int hashCode() {
        int result = getLogin() != null ? getLogin().hashCode() : 0;
        result = 31 * result + (getGroupName() != null ? getGroupName().hashCode() : 0);
        return result;
    }
}
