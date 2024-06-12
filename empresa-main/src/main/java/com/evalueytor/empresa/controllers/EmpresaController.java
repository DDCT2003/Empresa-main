package com.evalueytor.empresa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalueytor.empresa.models.Empresa;
import com.evalueytor.empresa.models.Proveedor;
import com.evalueytor.empresa.repositories.EmpresaRepository;
import com.evalueytor.empresa.repositories.ProveedorRepository;


@RestController
@RequestMapping("/api/empresa")


public class EmpresaController {
    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    ProveedorRepository proveedorRepository;
    // Listar todo
    @GetMapping("empresa/findall")
    public List<Empresa> listEmpresa() {
        return empresaRepository.findAll();
    }

    // Listar por Id
    @GetMapping("empresa/findbyid/{id}")
    public ResponseEntity<Empresa> obtenerEmpresaPorId(@PathVariable Long id) {
        Optional<Empresa> premioOptional = empresaRepository.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva empresa
    @PostMapping("empresa/save")
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa nuevoEmpresa) {
        Empresa EmpresaGuardada = empresaRepository.save(nuevoEmpresa);
        return new ResponseEntity<>(EmpresaGuardada, HttpStatus.CREATED);
    }

    // Actualizar empresa
    @PutMapping("empresa/updatebyid/{id}")
    public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaActual) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        return empresaOptional.map(empresa -> {
            empresa.setId(id);
            empresa.setNombre(empresaActual.getNombre());
            empresa.setDireccion(empresaActual.getDireccion());
            empresa.setTelefono(empresaActual.getTelefono());
            Empresa empresaActualGuardado = empresaRepository.save(empresa);
            return new ResponseEntity<>(empresaActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un empresa por ID
    @DeleteMapping("empresa/deletebyid/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            empresaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

        // Listar todo
        @GetMapping("proveedor/findall")
        public List<Proveedor> listProveedor() {
            return proveedorRepository.findAll();
        }
    
        // Listar por Id
        @GetMapping("proveedor/findbyid/{id}")
        public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Long id) {
            Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
            return proveedorOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    
        // Crear una nueva proveedor
         @PostMapping("proveedor/save")
         public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor nuevoProveedor) {
        Proveedor proveedorGuardado = proveedorRepository.save(nuevoProveedor);
        return new ResponseEntity<>(proveedorGuardado, HttpStatus.CREATED);
    }
    
        // Actualizar proveedor
        @PutMapping("proveedor/updatebyid/{id}")
        public ResponseEntity<Proveedor> actualizarEmpresa(@PathVariable Long id, @RequestBody Proveedor proveedorActual) {
            Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
            return proveedorOptional.map(proveedor -> {
                proveedor.setId(id);
                proveedor.setNombre(proveedorActual.getNombre());
                proveedor.setDireccion(proveedorActual.getDireccion());
                proveedor.setTelefono(proveedorActual.getTelefono());
                proveedor.setCalificacion(proveedorActual.getCalificacion());
                Proveedor proveedorActualGuardado = proveedorRepository.save(proveedor);
                return new ResponseEntity<>(proveedorActualGuardado, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    
        // Eliminar un proveedor por ID
        @DeleteMapping("proveedor/deletebyid/{id}")
        public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
            Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
            if (proveedorOptional.isPresent()) {
                proveedorRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

}
