package cz.muni.fi.pa165.airportmanager.sampledata;

import java.io.IOException;

/**
 * Populates database with sample data.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
public interface SampleDataLoadingFacade {

    void loadData() throws IOException;
}
