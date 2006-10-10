package pal.plugins.intellij.jumptousage.ui;

import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.PopupChooserBuilder;
import pal.plugins.intellij.jumptousage.util.ReferenceNavigator;
import pal.plugins.intellij.jumptousage.wrappers.DataHolder;
import pal.plugins.intellij.jumptousage.wrappers.Reference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

public class PopupListPresentation implements Presentable {
    private Collection collection;
    private String popupTitle;

    public PopupListPresentation(Collection collection, String popupTitle) {
        this.collection = collection;
        this.popupTitle = popupTitle;
    }

    public void present(PresentationLocation location) {
        buildPopup().showInScreenCoordinates(DataHolder.getInstance().EDITOR.getContentComponent(), location);
    }

    private JBPopup buildPopup() {
        PopupChooserBuilder popupChooserBuilder = new PopupChooserBuilder(createList());
        popupChooserBuilder.setTitle(popupTitle);
        JBPopup popup = popupChooserBuilder.createPopup();
        Component popupContentPane = popup.getContent();
        LookAndFeel.installBorder((JComponent) popupContentPane, "PopupMenu.border");
        LookAndFeel.installColorsAndFont((JComponent) popupContentPane, "PopupMenu.background",
                "PopupMenu.foreground", "PopupMenu.font");
        return popup;
    }

    private JList createList() {
        final JList list = new JList(collection.toArray());
        list.setCellRenderer(new ReferenceListCellRenderer());
        list.setSelectedIndex(collection.size() > 0 ? 0 : -1);
        list.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (list.getSelectedIndex() != -1) {
                        new ReferenceNavigator(((Reference) list.getSelectedValue())).navigateToReference();
                    }
                }
            }
        });
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (list.getSelectedIndex() != -1) {
                    new ReferenceNavigator(((Reference) list.getSelectedValue())).navigateToReference();
                }
            }
        });

        LookAndFeel.installBorder(list, "List.border");
        LookAndFeel.installColorsAndFont(list, "List.background", "List.foreground", "List.font");

        return list;
    }
}
