package net.teamfruit.mchelishield.asm;

import javax.annotation.Nullable;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import net.minecraft.launchwrapper.IClassTransformer;
import net.teamfruit.mchelishield.Log;
import net.teamfruit.mchelishield.asm.lib.VisitorHelper;
import net.teamfruit.mchelishield.asm.lib.VisitorHelper.TransformProvider;

public class PrivacyShieldTransformer implements IClassTransformer {
	@Override
	public @Nullable byte[] transform(final @Nullable String name, final @Nullable String transformedName, final @Nullable byte[] bytes) {
		if (bytes==null||name==null||transformedName==null)
			return bytes;

		if (transformedName.equals("mcheli.multiplay.MCH_MultiplayClient"))
			return VisitorHelper.apply(bytes, name, new TransformProvider(ClassWriter.COMPUTE_FRAMES) {
				@Override
				public ClassVisitor createVisitor(final String name, final ClassVisitor cv) {
					Log.log.info(String.format("Patching MCH_MultiplayClient (class: %s)", name));
					return new MCH_MultiplayClientVisitor(name, cv);
				}
			});

		return bytes;
	}
}