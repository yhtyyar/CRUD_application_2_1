package repository.impl;

import model.Region;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.RegionRepository;

import java.util.ArrayList;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {

    private Transaction transaction;

    @Override
    public Region getById(Long id) {

        Region region = new Region();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            region = session.get(Region.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return region;
    }


    @Override
    public Region create(String regionName) {

        Region region = new Region();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Long regionId = (Long) session.save(new Region(regionName));
            region = session.get(Region.class, regionId);

            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return region;
    }


    @Override
    public Region update(Long id, String regionName) {

        Region regionResult = new Region();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Region region = session.get(Region.class, id);
            region.setRegionName(regionName);

            session.update(region);
            regionResult = session.get(Region.class, id);

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }

        return regionResult;
    }


    @Override
    public void deleteById(Long id) {

        try ( Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Region region = session.get(Region.class, id);
            session.delete(region);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }


    @Override
    public List<Region> getAll() {

        List<Region> regionList = new ArrayList<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            regionList = session.createQuery("FROM Region", Region.class).list();
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return regionList;
    }
}
