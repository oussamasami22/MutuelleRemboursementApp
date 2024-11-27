package ma.ensa.system_metuelle.controllers;

import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import ma.ensa.system_metuelle.exceptions.ResourceAlreadyExistException;
import ma.ensa.system_metuelle.models.MedicamentReferentiel;
import ma.ensa.system_metuelle.resps.ApiResponse;
import ma.ensa.system_metuelle.services.implementations.MedRefServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/batch/dossier/medicament")
public class MedtController {
    private final MedRefServiceImpl medRefService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addMedicament(@RequestBody MedicamentReferentiel ref){
        try {
            MedicamentReferentiel medRef = medRefService.addTraitemnt(ref);
            return ResponseEntity.ok(new ApiResponse("upload success", medRef));
        } catch (ResourceAlreadyExistException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("upload failed", null));

        }
    }

    @PostMapping("/addmultiple")
    public ResponseEntity<ApiResponse> addMultipleMedicament(@RequestBody List<MedicamentReferentiel> refs){
        try {
            medRefService.addMultipleMedicaments(refs);
            return ResponseEntity.ok(new ApiResponse("upload success", null));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("upload failed", null));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("upload failed", null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        try {
            List<MedicamentReferentiel> refs= medRefService.getAllMedicaments();
            return ResponseEntity.ok(new ApiResponse("upload success", refs));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("upload failed", null));
        }

    }
}