package engine.completedQuiz;

import engine.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompletedService {

    @Autowired
    CompletedRepo repo;

    public void save(Completed completed){
        repo.save(completed);
    }

    public Page<Completed> getAllCompletedByUser(User user, int page){
        Pageable paging = PageRequest.of(page, 10, Sort.by("completedAt").descending());

        return repo.findAllByUser(user, paging);
    }
}
