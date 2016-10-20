package no.zeppelin.awarenesstest.data;

import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.Get;
import com.dellkan.robobinding.helpers.modelgen.ItemPresentationModel;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.dellkan.robobinding.helpers.modelgen.TwoStateGetSet;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

@ItemPresentationModel
@PresentationModel
public class ConsoleEntry extends PresentationModelWrapper implements Serializable {
    @Get
    String summary;

    @Get
    String entry;

    @TwoStateGetSet
    boolean showExtended = false;

    private Date date;

    @PresentationMethod
    public String getTimestamp() {
        return DateFormat.getDateTimeInstance().format(date);
    }

    public ConsoleEntry(String summary) {
        this.summary = summary;
        this.date = new Date();
    }

    public ConsoleEntry(String summary, String entry) {
        this.summary = summary;
        this.entry = entry;
        this.date = new Date();
    }

    @PresentationMethod
    public void toggle() {
        if (entry != null) {
            showExtended = !showExtended;
            refresh();
        }
    }
}
