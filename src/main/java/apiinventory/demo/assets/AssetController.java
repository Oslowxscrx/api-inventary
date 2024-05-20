package apiinventory.demo.assets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetsService assetService;

    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset createdAsset = assetService.save(asset);
        return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.findAll();
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable("id") Long id) {
        Asset asset = assetService.findById(id);
        if (asset == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable("id") Long id, @RequestBody Asset asset) {
        Asset existingAsset = assetService.findById(id);
        if (existingAsset == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        asset.setId(id); // Ensure the ID is set
        Asset updatedAsset = assetService.update(asset);
        return new ResponseEntity<>(updatedAsset, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable("id") Long id) {
        Asset existingAsset = assetService.findById(id);
        if (existingAsset == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        assetService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}