package eHealth.dto;

public class QueueDto {
    private long userId;

    private long practitionerId;

    public QueueDto(long userId, long practitionerId) {
        this.userId = userId;
        this.practitionerId = practitionerId;
    }

    public QueueDto() {
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
    }

    @Override
    public String toString() {
        return "QueueDto{" +
                "practitionerId=" + practitionerId +
                ", userId=" + userId +
                '}';
    }
}
