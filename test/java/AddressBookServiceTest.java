import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AddressBookServiceTest {
    @Test
    void givenContactsWhenAdded_ShouldReturnTheTotalCount() {
        AddressBookService addressBookService = new AddressBookService();
        List<AddressBookContacts> addressBookContacts = addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        Assertions.assertEquals(4, addressBookContacts.size());
    }

    @Test
    void givenContactWhenUpdated_ShouldSyncWithDB() {
        AddressBookService addressBookService = new AddressBookService();
        List<AddressBookContacts> addressBookContacts = addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        addressBookService.updateLastName("Piyush", "Naik");
        boolean result = addressBookService.AddressBookInSyncWithDB("Piyush");
        Assertions.assertTrue(result);
    }

    @Test
    void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        LocalDate startDate = LocalDate.of(2020, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<AddressBookContacts> addressBookContacts = addressBookService.readAddressBookForDateRange(AddressBookService
                                                         .IOService.DB_IO,startDate, endDate);
        Assertions.assertEquals(4, addressBookContacts.size());
    }

    @Test
    public void givenAddressBook_WhenRetrieved_ShouldReturnCountOfCity() {
        AddressBookService addressBookService = new AddressBookService();
        int NumberOfContactWithCity = addressBookService.countAddressbookByCity("Jalgaon");
        Assertions.assertEquals(4,NumberOfContactWithCity);
    }

    @Test
    public void givenAddresBookDetails_WhenAdded_ShouldSyncWithDB() throws SQLException {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        addressBookService.addNewContact("Kaustubh", "Mujumdar", "Sagar park", "Jalgaon", "Maharashtra", 425001, 9665654670, "kaustubh@email", "2020-01-02");
        boolean result = addressBookService.AddressBookInSyncWithDB("vikas");
        Assertions.assertTrue(result);
    }
}
