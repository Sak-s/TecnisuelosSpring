/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tecnisuelos1.Controllers;


import com.example.tecnisuelos1.entity.Contador;
import com.example.tecnisuelos1.services.ContadorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author santiago
 */
@Controller
@RequestMapping

public class ContadorController {
    
    @Autowired
        private ContadorService contadorInterface;


    @GetMapping("/crudContador")
    public String vistaCrudContador(Model model) {
        List<Contador> listaCliente = contadorInterface.getCliente();
        model.addAttribute("monalisa", listaCliente);
        return "crudC/crudContador";
    }

    @GetMapping("/agregarCliente")
    public String vistaAgregar(Model model) {
        return "crudC/agregarCliente";

    }    
    
    @PostMapping("/guardarCliente")
    public String GuardarCliente(@ModelAttribute Contador contador, BindingResult resultado) {
        contadorInterface.crearCliente(contador);
        return "redirect:/agregarCliente";
    }


    @RequestMapping("/verEditar/{id}")
    public String verFormularioEdicion(@PathVariable Long id, Model modelo) {
        
        Contador clienteActual = contadorInterface.buscarById(id);
        if (clienteActual != null) {
           modelo.addAttribute("cliente", clienteActual);
        }
        return "crudC/editarCrud";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizaUsuario(@PathVariable  Long id, Contador contador,  BindingResult resultado, Model modelo ){
        
        contadorInterface.ActualizarCliente(contador);
        return "redirect:/crudContador";
        
    }

  
    @GetMapping("/eliminarCliente/{id}")
    public String eliminaUsuario(@PathVariable  Long id) {
        contadorInterface.borrarCliente(id);
        return "redirect:/crudContador";
    }
    
    
    /*@GetMapping("/actualizar")
    public String updateCliente(@ModelAttribute Contador contador, BindingResult resultado) {
        contadorInterface.ActualizarCliente(contador);
        return "redirect:/crudContador";
    }*/

    

   
}
