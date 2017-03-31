package net.teamfruit.mchelishield.asm;

import javax.annotation.Nullable;

import net.minecraft.launchwrapper.IClassTransformer;

public class PrivacyShieldTransformer implements IClassTransformer {
	@Override
	public @Nullable byte[] transform(final @Nullable String name, final @Nullable String transformedName, final @Nullable byte[] bytes) {
		if (bytes==null||name==null||transformedName==null)
			return bytes;

		//		if (transformedName.equals("net.minecraft.client.gui.GuiScreenBook"))
		//			return VisitorHelper.apply(bytes, name, new TransformProvider(ClassWriter.COMPUTE_FRAMES) {
		//				@Override
		//				public ClassVisitor createVisitor(final String name, final ClassVisitor cv) {
		//					Log.log.info(String.format("Patching GuiScreenBook.drawScreen (class: %s)", name));
		//					return new GuiScreenBookVisitor(name, cv);
		//				}
		//			});

		return bytes;
	}
}