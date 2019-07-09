package bleach.hack.gui.clickgui;

import bleach.hack.BleachHack;
import bleach.hack.module.Category;
import bleach.hack.module.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ClickGuiScreen extends Screen {
	private int len;
	private int setLen;
	
	private boolean lMousePressed = false;
	private boolean rMousePressed = false;
	private boolean dragging = false;
	
	ModuleWindow modsExp, modsWrd, modsCmb, modsPly, modsMvm, modsRen;
	
	public ClickGuiScreen(ITextComponent titleIn) {
		super(new StringTextComponent("ClickGui"));
	}
	
	public void initWindows() {
		modsExp = new ModuleWindow(this, ModuleManager.getModulesInCat(Category.EXPLOITS), "Exploits", len, setLen, 30, 35);
		modsWrd = new ModuleWindow(this, ModuleManager.getModulesInCat(Category.WORLD), "World", len, setLen, 100, 35);
		modsCmb = new ModuleWindow(this, ModuleManager.getModulesInCat(Category.COMBAT), "Combat", len, setLen, 170, 35);
		modsPly = new ModuleWindow(this, ModuleManager.getModulesInCat(Category.PLAYER), "Player", len, setLen, 240, 35);
		modsMvm = new ModuleWindow(this, ModuleManager.getModulesInCat(Category.MOVEMENT), "Movement", len, setLen, 310, 35);
		modsRen = new ModuleWindow(this, ModuleManager.getModulesInCat(Category.RENDER), "Render", len, setLen, 380, 35);
	}
	
	public void onClose() {
		ModuleManager.getModuleByName("ClickGui").setToggled(false);
		this.minecraft.displayGuiScreen(null);
	}
	
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		this.renderBackground();
		font.drawString("BleachHack-1.14-" + BleachHack.VERSION, 3, 3, 0x305090);
		font.drawString("BleachHack-1.14-" + BleachHack.VERSION, 2, 2, 0x6090d0);
		modsExp.draw(p_render_1_, p_render_2_, lMousePressed, rMousePressed, len, setLen, dragging);
		modsWrd.draw(p_render_1_, p_render_2_, lMousePressed, rMousePressed, len, setLen, dragging);
		modsCmb.draw(p_render_1_, p_render_2_, lMousePressed, rMousePressed, len, setLen, dragging);
		modsPly.draw(p_render_1_, p_render_2_, lMousePressed, rMousePressed, len, setLen, dragging);
		modsMvm.draw(p_render_1_, p_render_2_, lMousePressed, rMousePressed, len, setLen, dragging);
		modsRen.draw(p_render_1_, p_render_2_, lMousePressed, rMousePressed, len, setLen, dragging);
		
		lMousePressed = false;
		rMousePressed = false;
		dragging = false;
		len = (int) Math.round(ModuleManager.getModuleByName("ClickGui")
				.getSettings().get(0).toSlider().getValue());
		setLen = (int) Math.round(ModuleManager.getModuleByName("ClickGui")
				.getSettings().get(1).toSlider().getValue());
	}
	
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
		if(p_mouseClicked_5_ == 0) lMousePressed = true;
		else if(p_mouseClicked_5_ == 1) rMousePressed = true;
		
		return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
	}
	
	public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
		if(p_mouseDragged_5_ == 0) dragging = true;
		return super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
	}
}