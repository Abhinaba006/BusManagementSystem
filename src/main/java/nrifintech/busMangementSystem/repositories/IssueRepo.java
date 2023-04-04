package nrifintech.busMangementSystem.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.Issue;
import nrifintech.busMangementSystem.payloads.IssueResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

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

    // after this pagination
    @Query(value = "SELECT * FROM issue WHERE user_id = :userId and is_resolved = :is_resolved",nativeQuery = true)
	Page<Issue> findIssuesByUserId(@Param("userId") int userId, @Param("is_resolved") int is_resolved,Pageable pageable);

    @Query(value = "SELECT * FROM issue WHERE is_resolved = 0",nativeQuery = true)
    Page<Issue> findAllunResolvedIssue(Pageable pageable);

	@Query(value = "SELECT * FROM issue WHERE is_resolved = 0 and user_id=:userId",nativeQuery = true)
    Page<Issue> findUserunResolvedIssue(@Param("userId") int userId,Pageable pageable);

    @Query(value = "SELECT * FROM issue WHERE is_resolved = 1",nativeQuery = true)
	Page<Issue> findAllResolvedIssue(Pageable pageable);

    @Query(value = "SELECT * FROM issue WHERE is_resolved = 1 and user_id=:userId",nativeQuery = true)
	Page<Issue> findUserResolvedIssue(@Param("userId") int userId,Pageable pageable);
    


    // Page<Issue> findByUserIdAndStatus(int userId, int resolved, Pageable pageable);
}
