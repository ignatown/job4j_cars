package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Ad;
import ru.job4j.model.Body;
import ru.job4j.model.Brand;
import ru.job4j.model.User;

import java.util.List;
import java.util.function.Function;

public class HbnStore implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new HbnStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    private <T> T wrpSession(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl =  command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public List<Ad> findAllAds() {
        return wrpSession(session -> session.createQuery("from Ad a "
                + "left join fetch a.body left join fetch a.brand left join fetch a.user")
                .list());
    }

    @Override
    public List<Ad> findAdsForUser(int id) {
        return wrpSession(session -> session.createQuery("from Ad a "
                + "left join fetch a.body left join fetch a.brand left join fetch a.user where a.user.id=:id")
                .setParameter("id", id)
                .list());
    }

    @Override
    public List<Body> findAllBodies() {
        return wrpSession(session -> session.createQuery("from Body")
                .list());
    }

    @Override
    public List<Brand> findAllBrands() {
        return wrpSession(session -> session.createQuery("from Brand")
                .list());
    }

    @Override
    public void addUser(User user) {
      wrpSession(session -> session.save(user));
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) wrpSession(session -> session.createQuery("from User where email=:email")
                .setParameter("email", email).uniqueResult()
        );
    }

    @Override
    public void addAd(Ad ad, String[] bodyId, String[] brandId) {
        wrpSession(session -> {
            ad.setBody(session.find(Body.class, Integer.parseInt(bodyId[0])));
            ad.setBrand(session.find(Brand.class, Integer.parseInt(brandId[0])));
            session.save(ad);
            return true;
        });
    }

    @Override
    public void deleteAd(int id) {
        wrpSession(session -> {
            session.delete(session.find(Ad.class, id));
            return true;
        });
    }

    @Override
    public List<Ad> findAdsByToday() {
       return wrpSession(session -> session.createQuery("from Ad "
                + "where day(current_timestamp - timeOfCreated) <= 1")
                .list());
    }

    @Override
    public List<Ad> findAdsByBrand(String brandName) {
        return wrpSession(session -> session.createQuery("from Ad a "
                + "where a.brand=:brand"))
                .setParameter("brand", brandName)
                .list();
    }

    @Override
    public List<Ad> findAdsWithPhoto() {
        return wrpSession(session -> session.createQuery("from Ad "
                + " where hasPhoto = true"))
                .list();
    }

    @Override
    public void updatePhotoStatus(int userId, int itemId) {
        wrpSession(session -> session.createQuery("update Ad "
                + "set hasPhoto = true where id=:itemId and user.id=:userId"))
                .setParameter("userId", userId)
                .setParameter("itemId", itemId)
                .executeUpdate();
    }
}
