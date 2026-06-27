package com.j2htmx.auto.registry;

import com.j2htmx.auto.solar.DesktopIcon;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FolderRegistry {
    public static Map<DesktopIcon, LocalDateTime> openedFolders = new HashMap<>();
    public static PriorityQueue<DesktopIcon> pq = new PriorityQueue<>((a,b) -> openedFolders.get(a).compareTo(openedFolders.get(b)));
    public static final Map<String, DesktopIcon> folder = new HashMap<>();

    static {
        folder.put("Home",new DesktopIcon("","Home"));
        openedFolders.put(folder.get("Home"), LocalDateTime.now());

        pq.offer(folder.get("Home"));
    }






}
