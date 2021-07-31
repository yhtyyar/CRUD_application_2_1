package repository.impl;

import model.Region;
import model.Writer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.RegionRepository;
import repository.hibernate_utils.HibernateSessionFactoryRegion;

import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {


    @Override
    public Region getById(Long id) {
        return HibernateSessionFactoryRegion.getSessionFactory().openSession().get(Region.class, id);
    }


    @Override
    public Region create(Writer writerId, String regionName) {

        Session session = HibernateSessionFactoryRegion.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();


        Integer regionId = (Integer) session.save(new Region(writerId, regionName));
        Region region = session.get(Region.class, regionId);
        transaction.commit();
        session.close();

        return region;
    }


    @Override
    public Region update(Long id, Writer writerId, String regionName) {

        Session session = HibernateSessionFactoryRegion.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();


        Region region = session.get(Region.class, id);

        region.setWriter(writerId);
        region.setRegionName(regionName);
        session.update(region);
        Region regionResult = session.get(Region.class, id);

        transaction.commit();
        session.close();

        return regionResult;
    }


    @Override
    public void deleteById(Long id) {

        Session session = HibernateSessionFactoryRegion.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Region region = session.get(Region.class, id);
        session.delete(region);
        transaction.commit();
        session.close();
    }


    @Override
    public List<Region> getAll() {

        Session session = HibernateSessionFactoryRegion.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<Region> regionList = session.createQuery("SELECT R.id, R.writer, R.regionName  FROM Region AS R",
                Region.class).list();

        transaction.commit();
        session.close();

        return regionList;
    }
}
