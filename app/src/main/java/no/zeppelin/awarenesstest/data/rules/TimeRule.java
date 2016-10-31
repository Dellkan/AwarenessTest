package no.zeppelin.awarenesstest.data.rules;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.widget.TimePicker;

import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.TimeFence;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

import java.util.TimeZone;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.FenceEntry;

@PresentationModel
public class TimeRule extends BaseRule {
    private LocalTime start = LocalTime.MIDNIGHT;
    private LocalTime end = LocalTime.MIDNIGHT;

    public TimeRule(FenceEntry fence) {
        super(fence);
    }

    @Override
    public AwarenessFence createFence() {
        return TimeFence.inDailyInterval(TimeZone.getDefault(), start.getMillisOfDay(), end.getMillisOfDay());
    }

    @Override
    public int getLayout() {
        return R.layout.rule_config_time;
    }

    @SuppressLint("DefaultLocale")
    @Override
    @PresentationMethod
    public String getRuleTitle() {
        return String.format("the time is between %s and %s", start.toString(DateTimeFormat.shortTime()), end.toString(DateTimeFormat.shortTime()));
    }

    @Override
    @PresentationMethod
    public void configure() {
        save();
    }

    @Override
    @PresentationMethod
    public void save() {
        new TimePickerDialog(MainActivity.getInstance(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                start = new LocalTime(hourOfDay, minute);
                new TimePickerDialog(MainActivity.getInstance(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        end = new LocalTime(hourOfDay, minute);
                        TimeRule.super.save();
                    }
                }, end.getHourOfDay(), end.getMinuteOfHour(), true).show();
            }
        }, start.getHourOfDay(), start.getMinuteOfHour(), true).show();
    }
}
