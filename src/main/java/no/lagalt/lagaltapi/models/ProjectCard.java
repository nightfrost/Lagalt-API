package no.lagalt.lagaltapi.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "project_cards")
public class ProjectCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_card_id")
    private long projectCardId;

    @Column(name = "project_card_title")
    private String projectCardTitle;

    @Column(name = "project_card_text")
    private String projectCardText;

    @Column(name = "project_card_created_at")
    private Timestamp projectCardCreatedAt;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
