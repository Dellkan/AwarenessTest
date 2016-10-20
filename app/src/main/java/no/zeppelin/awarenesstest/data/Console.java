package no.zeppelin.awarenesstest.data;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;

import java.io.Serializable;

@PresentationModel
public class Console extends PresentationModelWrapper implements Serializable {
    @ListItems
    ListContainer<ConsoleEntry> entries = new ListContainer<>();

    public void addEntry(String summary) {
        this.entries.addItem(new ConsoleEntry(summary), 0);
        refresh("entries");
    }

    public void addEntry(String summary, String entry) {
        this.entries.addItem(new ConsoleEntry(summary, entry), 0);
        refresh("entries");
    }
}
