package mls.server_property.repositories;

import mls.server_property.domain.Property;
import mls.server_property.domain.Residential;
import org.springframework.data.repository.NoRepositoryBean;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ResidentialRepo extends PropertyRepo{

    Optional<Property> findResidentialByNOfParkingSpace(int nOfParkingSpace);
    Optional<Property> findResidentialByStorageType(String storageType);
    Optional<Property> findResidentialByNOfStorages(int nOfStorages);
    Optional<Property> findResidentialByBuiltDate(Date builtDate);
    Optional<List<Residential>> findResidentialByEntryDate(Date entryDate);
}
