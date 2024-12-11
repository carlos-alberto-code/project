package com.project;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Cargar variables de entorno desde .env
        Dotenv dotenv = Dotenv.configure().load();

        // Crear mapa de propiedades para la conexión
        Map<String, String> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.url", dotenv.get("DB_URL"));
        properties.put("jakarta.persistence.jdbc.user", dotenv.get("DB_USER"));
        properties.put("jakarta.persistence.jdbc.password", dotenv.get("DB_PASSWORD"));

        // Crear EntityManagerFactory con las propiedades
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectPU", properties);
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
