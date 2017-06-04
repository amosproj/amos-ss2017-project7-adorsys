package de.fau.amos.virtualledger.server.factories;

import de.fau.amos.virtualledger.dtos.BankAccountBookings;
import de.fau.amos.virtualledger.dtos.Booking;
import de.fau.amos.virtualledger.server.banking.model.BookingModel;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestScoped
public class BankAccountBookingsFactory {

    public BankAccountBookings createBankAccountBookings(final List<BookingModel> bookingModels, final String bankAccessId, final String bankAccountId)
    {
        final List<Booking> resultBookings = new ArrayList<>();
        final BankAccountBookings result = new BankAccountBookings(bankAccessId, bankAccountId, resultBookings);
        for (final BookingModel bookingModel : bookingModels) {
            resultBookings.add(new Booking(new Date(bookingModel.getBookingDate()), bookingModel.getAmount()));
        }

        return result;
    }

}