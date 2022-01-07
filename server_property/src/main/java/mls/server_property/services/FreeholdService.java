package mls.server_property.services;

import mls.server_property.repositories.FreeHoldRepo;



public abstract class FreeholdService extends PropertyService {
    private FreeHoldRepo freeHoldRepo;

    public FreeholdService(FreeHoldRepo repo) {
        super(repo);
        this.freeHoldRepo = repo;
    }
}
