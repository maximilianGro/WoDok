package eHealth.service.impl;

import eHealth.entity.Practitioner;
import eHealth.persistence.PractitionerDao;
import eHealth.service.PractitionerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PractitionerServiceImpl implements PractitionerService {
    private final PractitionerDao dao;

    public PractitionerServiceImpl(PractitionerDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Practitioner> allPractitioners() {
        return dao.getAll();
    }
}
