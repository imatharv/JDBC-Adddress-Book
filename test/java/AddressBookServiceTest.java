import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
}
