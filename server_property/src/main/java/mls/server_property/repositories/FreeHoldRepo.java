package mls.server_property.repositories;

import mls.server_property.domain.Freehold;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface FreeHoldRepo extends ResidentialRepo{
    Optional<List<Freehold>> findFreeholdByType(String type);
    Optional<List<Freehold>> findFreeholdByMultiGenerationType(boolean isMultigeneration);
    Optional<List<Freehold>> findFreeholdByNumberOfFloors(int numberOfFloors);
}
