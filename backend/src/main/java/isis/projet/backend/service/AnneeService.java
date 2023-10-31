package isis.projet.backend.service;
import isis.projet.backend.dao.AnneeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AnneeService {
    private final AnneeRepository anneeDao;

    public AnneeService(AnneeRepository anneeDao) {
        this.anneeDao = anneeDao;
    }

    @Transactional
    public long combienDanne() {
        return anneeDao.count();
    }
}
