package nrifintech.busMangementSystem.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.Issue;

public interface IssueRepo extends JpaRepository<Issue, Integer>{
    @Query(value = "SELECT * FROM issue WHERE user_id = :userId",nativeQuery = true)
	List<Issue> getIssuesByUserId(@Param("userId") int userId);

    @Query(value = "SELECT * FROM issue WHERE is_resolved = 0",nativeQuery = true)
    List<Issue> getAllunResolvedIssue();

	@Query(value = "SELECT * FROM issue WHERE is_resolved = 0 and user_id=:userId",nativeQuery = true)
    List<Issue> getUserunResolvedIssue(@Param("userId") int userId);

    @Query(value = "SELECT * FROM issue WHERE is_resolved = 1",nativeQuery = true)
	List<Issue> getAllResolvedIssue();

    @Query(value = "SELECT * FROM issue WHERE is_resolved = 1 and user_id=:userId",nativeQuery = true)
	List<Issue> getUserResolvedIssue(@Param("userId") int userId);

}
