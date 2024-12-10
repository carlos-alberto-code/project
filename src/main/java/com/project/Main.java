// src/main/java/com/project/Main.java
package com.project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Crear un nuevo usuario
            User usuario = new User("carlos", "carlos@example.com");
            em.persist(usuario);

            System.out.println("Usuario creado con éxito!");

            // Buscar el usuario
            User usuarioEncontrado = em.find(User.class, usuario.getId());
            System.out.println("Usuario encontrado: " + usuarioEncontrado);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}
