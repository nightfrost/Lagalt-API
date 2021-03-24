package no.lagalt.lagaltapi.services;

import no.lagalt.lagaltapi.models.Announcement;
import no.lagalt.lagaltapi.models.enums.ApprovalStatus;
import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import no.lagalt.lagaltapi.repositories.UsersProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;

@Service
public class UsersProjectsService {

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    public ResponseEntity<UsersProjects> getUsersProjectsByUserIdAndProjectId(long userId, long projectId) {
        UsersProjects returnUsersProjects = null;
        HttpStatus status;
        if (usersProjectsRepository.existsByUserUserIdAndProjectProjectId(userId, projectId)) {
            returnUsersProjects = usersProjectsRepository.findByUserUserIdAndProjectProjectId(userId, projectId);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnUsersProjects, status);
    }

    public ResponseEntity<UsersProjects> addUserProject(@RequestBody UsersProjects newUsersProjects) {
        HttpStatus status;
        UsersProjects usersProjects;
        long userId = newUsersProjects.getUser().getUserId();
        long projectId = newUsersProjects.getProject().getProjectId();
        if (usersProjectsRepository.existsByUserUserIdAndProjectProjectId(userId, projectId)) {
            usersProjects = usersProjectsRepository.findByUserUserIdAndProjectProjectId(userId, projectId);
            status = HttpStatus.SEE_OTHER;
        } else {
            usersProjects = usersProjectsRepository.save(newUsersProjects);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(usersProjects, status);
    }

    public ResponseEntity<UsersProjects> updateUsersProjectsByUserIdAndProjectId(long userId, long projectId, UsersProjects newUsersProjects) {
        HttpStatus status;
        if (usersProjectsRepository.existsByUserUserIdAndProjectProjectId(userId, projectId)) {
            UsersProjects usersProjects = usersProjectsRepository.findByUserUserIdAndProjectProjectId(userId, projectId);

            ApprovalStatus newApprovalStatus = newUsersProjects.getApprovalStatus();
            usersProjects.setApprovalStatus(newApprovalStatus);
            if (newApprovalStatus == ApprovalStatus.APPROVED) {
                usersProjects.setHasContributed(true);
            }

            String newMotivation = newUsersProjects.getMotivation();
            usersProjects.setMotivation(newMotivation);

            usersProjectsRepository.save(usersProjects);
            status = HttpStatus.OK;
            return new ResponseEntity<>(usersProjects, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

}
