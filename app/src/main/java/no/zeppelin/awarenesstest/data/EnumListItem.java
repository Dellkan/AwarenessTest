package no.zeppelin.awarenesstest.data;

import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.ItemPresentationModel;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;

import java.util.ArrayList;
import java.util.List;

@ItemPresentationModel
public class EnumListItem<T extends Enum> extends PresentationModelWrapper {
    T theEnum;

    public EnumListItem(T theEnum) {
        this.theEnum = theEnum;
    }

    public T getEnum() {
        return theEnum;
    }

    @PresentationMethod
    public String getEntryTitle() {
        return theEnum.name();
    }

    public static <T extends Enum> List<EnumListItem<T>> getList(T... enums) {
        List<EnumListItem<T>> list = new ArrayList<>();

        for (Enum theEnum : enums) {
            list.add(new EnumListItem<T>((T)theEnum));
        }

        return list;
    }

    public int ordinal() {
        return theEnum.ordinal();
    }

    public String name() {
        return theEnum.name();
    }
}
