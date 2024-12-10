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

            // Aquí irá tu código de prueba
            System.out.println("Conexión establecida con éxito!");

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
