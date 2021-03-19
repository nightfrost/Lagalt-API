package no.lagalt.lagaltapi.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long announcementId;

    @Column(name = "announcement_title")
    private String announcementTitle;

    @Column(name = "announcement_text")
    private String announcementText;

    @Column(name = "announcement_at")
    private Timestamp announcementCreatedAt;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
