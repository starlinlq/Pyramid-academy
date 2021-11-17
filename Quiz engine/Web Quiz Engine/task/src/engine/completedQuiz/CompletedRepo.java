package engine.completedQuiz;

import engine.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedRepo extends PagingAndSortingRepository<Completed, Long> {
    public Page<Completed> findAllByUser(User user, Pageable pageable);
}
