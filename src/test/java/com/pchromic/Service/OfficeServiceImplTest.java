
package com.pchromic.Service;

import com.pchromic.Entity.Worker;
import com.pchromic.Enum.WorkerPosition;
import com.pchromic.Repository.OfficeRepository;
import com.pchromic.TO.OfficeTO;
import com.pchromic.TO.TOBuilder.OfficeTOBuilder;
import com.pchromic.TO.TOBuilder.WorkerTOBuilder;
import com.pchromic.TO.WorkerTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OfficeServiceImplTest {

    @Autowired
    private
    OfficeService service;
    @Autowired
    private
    OfficeRepository officeRepository;
    @Autowired
    private
    WorkerService workerService;


    @Test
    public void shouldAddOffice () {

        // given
        OfficeTO newOffice = OfficeTOBuilder.anOfficeTO()
                .withAddress("ul.Jednosci 11, 55-111, Opole")
                .withEmail("office1@carrental.com")
                .withPhoneNumber("785854589")
                .withWorkerTOList(new ArrayList<>())
                .build();
        int repositorySizeBeforeAdd = officeRepository.findAll().size();

        // when
        service.addOffice(newOffice);
        int repositorySizeAfterAdd = officeRepository.findAll().size();
        // then
        assertNotNull(officeRepository.findAll());
        assertEquals(4, officeRepository.count());
        assertEquals(repositorySizeBeforeAdd,repositorySizeAfterAdd-1);
    }

    @Test
    public void shouldRemoveOffice () {

        // given
        OfficeTO testOffice = service.getOfficeById(1L);
        long repositorySizeBeforeRemove = officeRepository.count();
        // when
        service.removeOffice(testOffice.getId());
        long repositorySizeAfterRemove = officeRepository.count();

        // then
        assertEquals(repositorySizeAfterRemove,repositorySizeBeforeRemove-1);
    }
    @Test
    public void shouldUpdateOfficeDetails( ) {
        // given
        OfficeTO newOffice = service.getOfficeById(1L);

        // when
        newOffice.setEmail("officeissofun@office.com");
        service.updateOfficeDetails(newOffice);

        // then
        assertEquals(3,officeRepository.findAll().size());
        assertEquals("officeissofun@office.com",service.getOfficeById(1L).getEmail());
    }

    @Test
    public void shouldAddWorkerToOffice( ) {
        // given
        OfficeTO testOffice = service.getOfficeById(1L);
        WorkerTO testWorker = WorkerTOBuilder.aWorkerTO()
                .withBirthDate(LocalDate.of(2000, 1, 2))
                .withWorkerPosition(WorkerPosition.MANAGER)
                .withFirstName("Ryszard")
                .withLastName("Kaputsta")
                .withCars(new ArrayList<>())
                .withOffice(new OfficeTO())
                .build();
        Worker addedWorker = workerService.addWorker(testWorker);

        // when
        service.addWorkerToOffice(testOffice.getId(),addedWorker.getId());

        // then
        assertEquals(4,service.getOfficeById(1L).getWorkerTOList().size());
    }
    @Test
    public void shouldRemoveWorkerFromOffice( ) {
        // given
        OfficeTO testOffice = service.getOfficeById(1L);
        WorkerTO workerRemoved = testOffice.getWorkerTOList().get(1);

        // when
        service.removeWorkerFromOffice(testOffice.getId(),workerRemoved.getId());

        // then
        assertEquals(2,service.getOfficeById(1L).getWorkerTOList().size());
    }


}

