/*
 * Created by Angel Leon (@gubatron), Alden Torres (aldenml)
 * Copyright (c) 2011, 2012, FrostWire(TM). All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.frostwire.android.bittorrent.websearch.isohunt;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.frostwire.websearch.TorrentWebSearchResult;

/**
 * @author gubatron
 * @author aldenml
 *
 */
public class ISOHuntWebSearchResult implements TorrentWebSearchResult {

    private ISOHuntItem item;

    public ISOHuntWebSearchResult(ISOHuntItem item) {
        this.item = item;
    }

    public long getCreationTime() {
        //Thu, 29 Apr 2010 16:32:44 GMT
        SimpleDateFormat date = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        long result = System.currentTimeMillis();
        try {
            result = date.parse(item.pubDate).getTime();
        } catch (ParseException e) {
        }
        return result;
    }

    public String getFileName() {
        String titleNoTags = item.title.replace("<b>", "").replace("</b>", "");
        return titleNoTags + ".torrent";
    }

    public String getHash() {
        return item.hash;
    }

    public String getTorrentURI() {
        return item.enclosure_url;
    }

    public long getSize() {
        return Long.valueOf(item.length);
    }

    @Override
    public String getSource() {
        return "ISOHunt";
    }

    public int getRank() {
        try {
            return Integer.valueOf(item.Seeds);
        } catch (Exception e) {
            //oh well
            return 0;
        }
    }

    public String getTorrentDetailsURL() {
        return item.link;
    }

    @Override
    public String getDisplayName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDetailsUrl() {
        // TODO Auto-generated method stub
        return null;
    }
}
