/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDrinker420/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package org.bleachhack.module.mods;

import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;

import net.minecraft.util.Util;

public class StarGithub extends Module {

	public StarGithub() {
		super("StarGithub", KEY_UNBOUND, ModuleCategory.MISC, "just star it its cool i guess.");
	}

	public void onEnable(boolean inWorld) {
		try {
			Util.getOperatingSystem().open("https://github.com/JohanDevv/CrystalHack");
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setEnabled(false);
	}
}
