package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.dtos.CarRequest;
import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.entities.Customer;
import br.edu.fatecfranca.api.repositories.CarRepository;
import br.edu.fatecfranca.api.validators.ManufactureYearValidator;
import br.edu.fatecfranca.api.validators.RequestValidator;
import br.edu.fatecfranca.api.validators.SaleDataValidator;
import br.edu.fatecfranca.api.validators.UniquePlatesValidator;

@Service
public class CarService {

    private final CarRepository repository;
    private final RequestValidator<CarRequest> validator;

    private void copyToEntity(
        CarRequest request,
        Car car) {

        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setColor(request.color());
        car.setYearManufacture(request.yearManufacture() != null ? request.yearManufacture().intValue() : null);
        car.setImported(request.imported());
        car.setPlates(request.plates());
        car.setSellingDate(request.sellingDate());
        car.setSellingPrice(request.sellingPrice());

        if (request.customerId() != null) {
            Customer customer = new Customer();
            customer.setId(request.customerId());
            car.setCustomer(customer);
        } else {
            car.setCustomer(null);
        }
    }

    public CarService(CarRepository repository) {
        this.repository = repository;

        // Montagem da Cadeia de Responsabilidade: 1 -> 2 -> 3
        RequestValidator<CarRequest> platesValidator =
            new UniquePlatesValidator(repository, null);

        RequestValidator<CarRequest> saleValidator =
            new SaleDataValidator(platesValidator);

        this.validator = new ManufactureYearValidator(saleValidator);
    }

    public Car create(CarRequest request) {
        // Validação no CREATE
        validator.validate(request, null);

        Car car = new Car();
        copyToEntity(request, car);
        return repository.save(car);
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return repository.findById(id);
    }

    public Car update(Long id, CarRequest request) {
        // Validação no UPDATE
        validator.validate(request, id);

        Car car = new Car();
        copyToEntity(request, car);
        car.setId(id);
        return repository.save(car);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}