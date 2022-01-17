package mls.server_property.services;

import mls.server_property.domain.*;
import mls.server_property.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("propServ")
public class PropertyService {
    @Autowired private PropertyRepo<Property> propertyRepo;

    @Autowired private LandRepo landRepo;
    @Autowired private ResidentialRepo<Residential> residentialRepo;
    @Autowired private FreeholdRepo<Freehold> freeholdRepo;
    @Autowired private CooperativeHomeRepo cooperativehomeRepo;
    @Autowired private CondominiumRepo<Condominium> condominiumRepo;
    @Autowired private MobileHomeRepo mobilehomeRepo;

    @Autowired private SemiDetachedRepo semidetachedRepo;
    @Autowired private VacationHomeRepo vacationhomeRepo;
    @Autowired private DetachedHomeRepo detachedhomeRepo;

    @Autowired private FarmHouseRepo farmhouseRepo;
    @Autowired private MultiLexRepo multilexRepo;
    @Autowired private TownHouseRepo townhouseRepo;

    @Autowired private TripleDeckersRepo tripledeckersRepo;
    @Autowired private CondoRepo condoRepo;
    @Autowired private StackedTownHouseRepo stackedtownhouseRepo;

    public List<Property> getAllProperties(){ return propertyRepo.findAll(); }

    public Property getProperties(Long id) throws Throwable {
        return propertyRepo.findById(id).orElseThrow(() -> new IllegalStateException(
            String.format("No property with id %d exists", id))); }

    public List<Property> getProperties(List<Long> ids){
        return propertyRepo.findAllById(ids);
    }

    public Optional<? extends List<? extends Property>> getProperties(String type, String partialAddress, int lowerBound, int upperBound)
                                                    throws IllegalArgumentException {
        switch (type.toLowerCase()) {
            case "property":
                return propertyRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
                // no need to "break;" because already return
            case "land":
                return landRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "residential":
                return residentialRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "freehold":
                return freeholdRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "cooperativehome":
                return cooperativehomeRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "condominium":
                return condominiumRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "mobilehome":
                return mobilehomeRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "semidetached":
                return semidetachedRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "vacationhome":
                return vacationhomeRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "detachedhome":
                return detachedhomeRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "farmhouse":
                return farmhouseRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "multilex":
                return multilexRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "townhouse":
                return townhouseRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "tripledeckers":
                return tripledeckersRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "condo":
                return condoRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            case "stackedtownhouse":
                return stackedtownhouseRepo.findByAddressContainsAndPriceBetween(partialAddress, lowerBound, upperBound);
            default:
                throw new IllegalArgumentException("Illegal property type");
        }

    }

    public void addNewProperty(Property property){
        Optional<Property> propOpt = propertyRepo.findByAddress(property.getAddress());
        if(propOpt.isPresent()){
            throw new IllegalStateException("Property exists at this address");
        }
        propertyRepo.save(property);
    }

    public void removeProperty(Long id){
        if(! propertyRepo.existsById(id)){
            throw new IllegalStateException(String.format("No property with id %d exists", id));
        }
        propertyRepo.deleteById(id);
    }

    @Transactional
    public void updateProperty(Long id, String address, int price) throws Throwable {
        Property property = propertyRepo.findById(id).orElseThrow(() -> new IllegalStateException(
                String.format("No property with id %d exists", id)));
        if(property.getPrice() != price)
            property.setPrice(price);
        if(! property.getAddress().equals(address))
            property.setAddress(address);
    }
}
