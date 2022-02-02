/**
 * Copyright 2022  Nikita S. <nikita@saraeff.net>
 * <p>
 * This file is part of wipexif.
 * <p>
 * wipexif is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * wipexif is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with wipexif.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.evildayz.code.wipexif;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class TheApp implements Runnable {
    private final String args[];

    TheApp(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        try {
            File f = new File(args[0]);
            BufferedImage bi = ImageIO.read(f);
            ImageIO.write(bi, "jpg", new File(String.format("%s.wiped.jpg", f.getName())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TheApp(args).run();
    }
}
