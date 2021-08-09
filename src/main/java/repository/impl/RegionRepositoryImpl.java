package repository.impl;

import model.Region;
import model.Writer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.RegionRepository;

import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {

    private Transaction transaction;

    @Override
    public Region getById(Long id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Region.class, id);
    }


    @Override
    public Region create( String regionName) {

        Region region = new Region();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();



            Long regionId = (Long) session.save(new Region( regionName));
            region = session.get(Region.class, regionId);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return region;
    }


    @Override
    public Region update(Long id, String regionName) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();


        Region region = session.get(Region.class, id);

        region.setRegionName(regionName);
        session.update(region);
        Region regionResult = session.get(Region.class, id);

        transaction.commit();
        session.close();

        return regionResult;
    }


    @Override
    public void deleteById(Long id) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Region region = session.get(Region.class, id);
        session.delete(region);
        transaction.commit();
        session.close();
    }


    @Override
    public List<Region> getAll() {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<Region> regionList = session.createQuery("SELECT R.id, R.writer, R.regionName  FROM Region AS R",
                Region.class).list();

        transaction.commit();
        session.close();

        return regionList;
    }
}
