package com.j2htmx.auto.registry;

import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.solar.DesktopIcon;
import com.j2htmx.auto.solar.Window;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


@Service
public class FolderRegistry {
    public static Map<DesktopIcon, LocalDateTime> openedFolders = new HashMap<>();
    public static PriorityQueue<DesktopIcon> pq = new PriorityQueue<>((a,b) -> openedFolders.get(a).compareTo(openedFolders.get(b)));
    public static final Map<String, DesktopIcon> folder = new HashMap<>();
    public static final Map<DesktopIcon, Window> associatedWindow = new HashMap<>();
    static {
        folder.put("Home",new DesktopIcon("","Home"));
        openedFolders.put(folder.get("Home"), LocalDateTime.now());

        pq.offer(folder.get("Home"));
    }

    public boolean isLegalName(String name) {
        return !folder.containsKey(name);
    }
    public void createFolder(String folderName, DesktopIcon icon) {
        folder.put(folderName , icon);
        associatedWindow.put(icon, new Window(folderName, folderName, new Div().text(folderName)).id(folderName).draggable());
    }

    public Window getFolder(String folderName) {
        openedFolders.put(folder.get(folderName), LocalDateTime.now());
        pq.offer(folder.get(folderName));
        return associatedWindow.get(folder.get(folderName));
    }





}
