package eHealth.persistence;

import eHealth.entity.Practitioner;

import java.util.List;

/**
 * Data Access Object for practitioners.
 * Implements access functionality to the application's persistent data store regarding practitioners.
 */
public interface PractitionerDao {
    /**
     * Get all practitioners stored in the persistent data store.
     * @return a list of all stored practitioners
     */
    List<Practitioner> getAll();
}
