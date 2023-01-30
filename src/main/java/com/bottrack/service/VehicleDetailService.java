package com.bottrack.service;

import com.bottrack.model.VehicleDetail;
import com.bottrack.repository.VehicleDetailRepository;
import com.bottrack.serviceinterfaces.IVehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleDetailService implements IVehicleDetailService {

    @Autowired
    VehicleDetailRepository vehicleDetailRepository;


    public String addVehicleDetailService(VehicleDetail vehicleDetail) {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        vehicleDetail.setCreatedOn(date);
        var result = this.vehicleDetailRepository.save(vehicleDetail);
        return "New VehicleDetail has been added";
    }

    public ResponseEntity<Object> updateVehicleDetailByVehicleNoService(VehicleDetail vehicleDetail, long vehicleNo) throws IOException {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        vehicleDetail.setCreatedOn(date);
        Optional<VehicleDetail> result = this.vehicleDetailRepository.findById(vehicleNo);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        vehicleDetail.setVehicleNo(vehicleNo);
        vehicleDetailRepository.save(vehicleDetail);
        return ResponseEntity.noContent().build();
    }

    public List<VehicleDetail> getAllVehicleDetailService() {
        var result = this.vehicleDetailRepository.findAll();
        return result;
    }

    public Optional<VehicleDetail> getVehicleDetailByVehicleNoService(long vehicleNo) {
        var result = this.vehicleDetailRepository.findById(vehicleNo);
        return result;

    }

    public String deleteVehicleDetailByVehicleNoService(long vehicleNo) {
       this.vehicleDetailRepository.deleteById(vehicleNo);
       return "VehicleDetail has been deleted";
    }
}
