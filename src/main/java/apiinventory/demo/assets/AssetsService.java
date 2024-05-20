package apiinventory.demo.assets;

import apiinventory.demo.employees.Employee;
import apiinventory.demo.employees.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AssetsService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Asset save(Asset asset) {
        if (asset.getEmployee() != null && asset.getEmployee().getId() != null) {
            Employee employee = employeeRepository.findById(asset.getEmployee().getId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            asset.setEmployee(employee);
        }
        return assetRepository.save(asset);
    }

    public List<Asset> findAll() {
        Iterable<Asset> iterable = assetRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                            .collect(Collectors.toList());
    }

    public Asset findById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }

    public Asset update(Asset asset) {
        asset.setFechaAsignacion(new Date()); // Establecer la fecha de asignación antes de guardar
        return assetRepository.save(asset);
    }

    public void deleteById(Long id) {
        assetRepository.deleteById(id);
    }
}