/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDrinker420/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package org.bleachhack.gui.clickgui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import org.bleachhack.gui.clickgui.window.ModuleWindow;
import org.bleachhack.gui.clickgui.window.UIContainer;
import org.bleachhack.gui.clickgui.window.UIWindow;
import org.bleachhack.module.ModuleManager;
import org.bleachhack.util.io.BleachFileHelper;

import java.util.Arrays;

public class UIClickGuiScreen extends ClickGuiScreen {

	private UIContainer uiContainer;
	private Screen parent;

	public UIClickGuiScreen(Screen parent, UIContainer uiContainer) {
		super(new LiteralText("UI Editor"));
		this.parent = parent;
		this.uiContainer = uiContainer;
	}

	public void init() {
		super.init();

		clearWindows();
		uiContainer.windows.values().forEach(this::addWindow);

		addWindow(new ModuleWindow(Arrays.asList(ModuleManager.getModule("UI")),
				200, 200, 75, "Render", new ItemStack(Items.YELLOW_STAINED_GLASS)));
	}

	public void onClose() {
		client.setScreen(parent);
	}

	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		BleachFileHelper.SCHEDULE_SAVE_UI.set(true);

		uiContainer.updatePositions(width, height);

		for (UIWindow w: uiContainer.windows.values()) {
			boolean shouldClose = w.shouldClose();
			if (shouldClose && !w.closed)
				w.detachFromOthers(false);

			w.closed = shouldClose;
		}

		super.render(matrices, mouseX, mouseY, delta);
	}
}
